//IIFE
(function () {

	jQuery(main);

	var tbody;
	var template;
	var userService = new UserServiceClient();

	function main() {
		tbody = $("tbody");
		template = $(".template");
		$("#createBtn").click(createUser);
		redrawUsers();
	}

	function createUser() {
		var username = $("#usernameFld").val();
		var password = $("#passwordFld").val();
		var firstName = $("#firstNameFld").val();
		var lastName = $("#lastNameFld").val();
		var role = $("#roleFld").val();

		var user = new User(username, password, "test@example.com",
							firstName, lastName, "5555555555", role, "1997-05-15");

		userService.createUser(user).then(redrawUsers);

	}

	function redrawUsers() {
		userService
			.findAllUsers()
			.then(drawUsers);
	}

	function drawUsers(users) {
		tbody.empty();
		for (var i=0; i<users.length; i++) {
			var user = users[i];
			var clone = template.clone();
			clone.find(".delete").click(deleteUser);
			clone.find(".edit").click(editUser);
			clone.find(".username").html(user.getUsername());
			clone.find(".firstName").html(user.getFirstName());
			clone.find(".lastName").html(user.getLastName());

			clone
				.attr("id", user.id)
				.attr("class", "userRow");
			tbody.append(clone);
		}
	}

	function deleteUser(event) {
		var button = $(event.currentTarget);
		var id = button.parent().parent().attr("id");
		userService.deleteUser(id)
			.then(redrawUsers);
	}

	function editUser(event) {

	}

})();