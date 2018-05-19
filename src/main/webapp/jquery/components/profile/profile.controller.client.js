(function () {

	var userService = new UserServiceClient();
	var $userid;
	var $username;
	var $password;
	var $email;
	var $firstName;
	var $lastName;
	var $phone;
	var $role;
	var $dob;
	var $updateBtn;
	$(main);

	function main() {
		$userid = $("#userid");
		$username = $("#username");
		$password = $("#password");
		$email = $("#email");
		$firstName = $("#firstName");
		$lastName = $("#lastName");
		$phone = $("#phone");
		$role = $("#role");
		$dob = $("#dob");
		$updateBtn = $("#updateBtn");
		$updateBtn.click(updateUser);
		findUserById(2);

	}

	function updateUser() {
		var user = new User ({
			username: $username.val(),
			password: $password.val(),
			email: $email.val(),
			firstName: $firstName.val(),
			lastName: $lastName.val(),
			phone: $phone.val(),
			role: $role.val(),
			dob: $dob.val()
		});

		userService.updateUser($userid.val(), user).then(function (response) {
			if (response === null) {
				alert("Unable to update");
			} else {
				alert("Success!");
				return response;
			}
		}).then(function(user) {findUserById(user.id)});
	}

	function findUserById(id) {
		userService
			.findUserById(id)
			.then(renderUser);
	}

	function renderUser(user) {
		$userid.val(user.id);
		$username.val(user.username);
		$password.val(user.password);
		$email.val(user.email);
		$firstName.val(user.firstName);
		$lastName.val(user.lastName);
		$phone.val(user.phone);
		$role.val(user.role);
		$dob.val(user.dob);
	}

})();