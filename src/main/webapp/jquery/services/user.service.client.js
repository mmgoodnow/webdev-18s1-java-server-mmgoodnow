function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.deleteUser = deleteUser;
	this.findUserById = findUserById;
	this.updateUser = updateUser;
	this.login = login;
	this.register = register;
	this.profile = profile;
	this.logout = logout;
	this.url = "/api/user";
	this.loginUrl = "/api/login";
	this.registerUrl = "/api/register";
	this.profileUrl = "/api/profile";
	this.logoutUrl = "/api/logout";
	var self = this;

	function login(username, password) {
		return fetch(self.loginUrl, {
			method: "post",
			body: JSON.stringify({username: username, password: password}),
			credentials: "same-origin",
			headers: {
				"content-type": "application/json"
			}
		});
	}

	function updateUser(userId, user) {
		return fetch(self.url + "/" + userId,
			{
				method: "put",
				body: JSON.stringify(user),
				headers: {"content-type": "application/json"}
			})
			.then(function (response) {
				return response.json();
			}, function () {
				return null;
			});
	}

	function findUserById(userId) {
		return fetch(self.url + "/" + userId)
			.then(function (response) {
				return response.json();
			});
	}

	function profile() {
		return fetch(self.profileUrl, {
			credentials: "same-origin"
		}).then(function (response) {
			return response.json();
		});
	}

	function logout() {
		return fetch(self.logoutUrl, {
			method: "post",
			credentials: "same-origin"
		});
	}

	function deleteUser(userId) {
		return fetch(self.url + "/" + userId,
			{
				method: "delete"
			});
	}

	function findAllUsers() {
		return fetch(self.url)
			.then(function (response) {
				return response.json();
			});
	}

	function createUser(user) {
		return fetch(self.url, {
			method: "post",
			body: JSON.stringify(user),
			headers: {
				"content-type": "application/json"
			}
		});
	}

	function register(user) {
		return fetch(self.registerUrl, {
			method: "post",
			body: JSON.stringify(user),
			credentials: "same-origin",
			headers: {
				"content-type": "application/json"
			}
		});
	}
}