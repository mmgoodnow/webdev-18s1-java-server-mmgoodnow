//IIFE
(function () {

	$(main);

	var $tbody;
	var $template;
	var $inputFields;
	var $username, $password, $firstName, $lastName, $role;
	var userService = new UserServiceClient();

	function main() {
		$tbody = $("tbody");
		$inputFields = $(".inputFields");
		$template = $(".template");
		$username = $("#usernameFld");
		$password = $("#passwordFld");
		$firstName = $("#firstNameFld");
		$lastName = $("#lastNameFld");
		$role = $("#roleFld");
		$("#createBtn").click(createUser);
		$("#updateBtn").click(updateUser);
		redrawUsers();
	}

	function createUser() {
		var user = new User(
			{
				username: $username.val(),
				password: $password.val(),
				firstName: $firstName.val(),
				lastName: $lastName.val(),
				role: $role.val()
			});

		userService.createUser(user).then(redrawUsers);
		clearInputs();
	}

	function redrawUsers() {
		userService
			.findAllUsers()
			.then(drawUsers);
	}

	function drawUsers(users) {
		$tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user = new User(users[i]);
			var clone = $template.clone();
			clone.find(".deleteBtn").click(deleteUser);
			clone.find(".editBtn").click(editUser);
			clone.find(".username").html(user.getUsername());
			clone.find(".firstName").html(user.getFirstName());
			clone.find(".lastName").html(user.getLastName());
			clone.find(".role").html(user.getRole());
			clone
				.attr("id", user.id)
				.attr("class", "userRow");
			$tbody.append(clone);
		}
	}

	function findUserById(id) {
		return userService
			.findUserById(id)
			.then(function (json) {
				return new User(json);
			});
	}

	function updateUser() {
		if (!$inputFields.attr("id")) return;

		var id = $inputFields.attr("id");
		$inputFields.removeAttr("id");
		var user = new User(
			{
				id: id,
				username: $username.val(),
				password: $password.val(),
				firstName: $firstName.val(),
				lastName: $lastName.val(),
				role: $role.val()
			});

		userService.updateUser(id, user).then(redrawUsers);
		clearInputs();
	}

	function deleteUser(event) {
		var button = $(event.currentTarget);
		var id = button.parent().parent().parent().attr("id");
		userService.deleteUser(id)
			.then(redrawUsers);
	}

	function editUser(event) {
		var button = $(event.currentTarget);
		var id = button.parent().parent().parent().attr("id");
		findUserById(id).then(populateInputs);

	}

	function clearInputs() {
		$username.val("");
		$password.val("");
		$firstName.val("");
		$lastName.val("");
	}

	function populateInputs(user) {
		$inputFields.attr("id", user.id);
		$username.val(user.getUsername());
		$password.val(user.getPassword());
		$firstName.val(user.getFirstName());
		$lastName.val(user.getLastName());
		$role.val(user.getRole());
	}

})();