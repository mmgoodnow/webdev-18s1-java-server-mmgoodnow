(function () {

	var userService = new UserServiceClient();
	var $username;
	var $password;
	var $login;
	var profileUrl = "/jquery/components/profile/profile.template.client.html";
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
					response.json().then(function () {
						window.location.assign(profileUrl);
					});
				}
			}, function () {
				alert("Login failed");
			});
	}

})();