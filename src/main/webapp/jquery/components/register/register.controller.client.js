(function () {

	var userService = new UserServiceClient();
	var $username;
	var $password;
	var $register;
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
					response.json().then(function (json) {
						window.location.assign(
							"/jquery/components/profile/profile.template.client.html?id="
							+ json.id);
					});
				}
			}, function () {
				alert("Registration failed");
			});
	}

})();