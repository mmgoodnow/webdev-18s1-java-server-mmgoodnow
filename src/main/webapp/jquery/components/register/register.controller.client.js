(function () {

	var userService = new UserServiceClient();
	var $username;
	var $password;
	var $register;
	$(main);

	function main() {
		$username = $("#username");
		$password = $("#password");
		$register = $("#updateBtn");
		$register.click(register);
	}

	function register() {
		function createUser() {
			var user = new User(
				{
					username: $username.val(),
					password: $password.val()
				});

			userService.createUser(user);
		}
	}

})();