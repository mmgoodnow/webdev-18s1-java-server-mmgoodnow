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
		var user = new User(
			{
				username: $username.val(),
				password: $password.val()
			});
		userService.register(user)
			.then(function () {
				alert("Registered")
			}, function () {
				alert("Registration failed")
			});
	}

})();