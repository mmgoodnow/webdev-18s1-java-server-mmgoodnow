//IIFE
(function () {

	jQuery(main);

	var tbody;
	var template;
	var userService = new UserServiceClient();

	function main() {
		tbody = $("tbody");
		template = $(".template");
		$("#createUser").click(createUser);
		redrawUsers();
	}

	function createUser() {
		var username = $("#usernameFld").val();
		var password = $("#passwordFld").val();
		var firstName = $("#firstNameFld").val();
		var lastName = $("#lastNameFld").val();

		var user = {
			username: username,
			password: password,
			firstName: firstName,
			lastName: lastName
		};

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
			clone.find(".username").html(user.username);
			clone.find(".firstName").html(user.firstName);
			clone.find(".lastName").html(user.lastName);

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