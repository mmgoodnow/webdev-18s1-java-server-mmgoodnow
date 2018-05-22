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
	var $logoutBtn;
	var loginUrl = "/jquery/components/login/login.template.client.html"
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
		$logoutBtn = $("#logoutBtn");
		$updateBtn.click(updateUser);
		$logoutBtn.click(logout);

		retrieveUserData();

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
		}).then(retrieveUserData);
	}

	function logout() {
		userService.logout();
		window.location.assign(loginUrl);
	}

	function retrieveUserData() {
		userService
			.profile()
			.then(renderUser);
	}

	function renderUser(json) {
		var user = new User(json);
		$userid.val(user.getID());
		$username.val(user.getUsername());
		$password.val(user.getPassword());
		$email.val(user.getEmail());
		$firstName.val(user.getFirstName());
		$lastName.val(user.getLastName());
		$phone.val(user.getPhone());
		$role.val(user.getRole());
		$dob.val(user.getDOB());
	}

})();