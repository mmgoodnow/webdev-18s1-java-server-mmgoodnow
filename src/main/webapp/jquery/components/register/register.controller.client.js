(function () {

	var userService = new UserServiceClient();
	var $username;
	var $password;
	var $register;
	var profileUrl = "/jquery/components/profile/profile.template.client.html"
	$(main);

	function main() {
		$username = $("#username");
		$password = $("#password");
		$register = $("#registerBtn");
		$register.click(register);
	}

	function register() {
		var user = new User(
			{
				username: $username.val(),
				password: $password.val()
			});
		userService.register(user)
			.then(function (response) {
				if (!response.ok) alert("Registration failed");
				else {
					alert("Registered");
					response.json().then(function () {
						window.location.assign(profileUrl)
					});
				}
			});
	}

})();