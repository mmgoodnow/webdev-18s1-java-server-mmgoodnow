(function () {

	var userService = new UserServiceClient();
	var $username;
	var $password;
	var $login;
	$(main);

	function main() {
		$username = $("#username");
		$password = $("#password");
		$login = $("#loginBtn");
		$login.click(login);
	}

	function login() {
		userService.login($username.val(), $password.val())
			.then(function (response) {
				if (!response.ok) alert("Login failed");
				else {
					response.json().then(function (json) {
						window.location.assign(
							"/jquery/components/profile/profile.template.client.html?id="
							+ json.id);
					});
				}
			}, function () {
				alert("Login failed");
			});
	}

})();