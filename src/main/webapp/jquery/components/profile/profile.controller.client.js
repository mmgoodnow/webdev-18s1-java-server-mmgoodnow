(function () {

	$(main);
	var userService = new UserServiceClient();
	var $username;
	var $firstName;
	var $lastName;
	var $updateBtn;

	function main() {
		$username = $("#username");
		$firstName = $("#firstName");
		$lastName = $("#lastName");
		$updateBtn = $("#updateBtn")
			.click(updateUser);
		findUserById(2442);

	}

	function updateUser() {
		var user = {
			firstName: $firstName.val(),
			lastName: $lastName.val()
		};

		userService.updateUser(2442, user).then(function (response) {
			if (response === null) {
				alert("Unable to update");
			} else {
				alert("Success!");
			}
		});
	}

	function findUserById(id) {
		userService
			.findUserById(id)
			.then(renderUser);
	}

	function renderUser(user) {
		$username.val(user.username);
		$firstName.val(user.firstName);
		$lastName.val(user.lastName);
	}

})();