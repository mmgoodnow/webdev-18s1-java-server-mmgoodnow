function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.deleteUser = deleteUser;
	this.findUserById = findUserById;
	this.updateUser = updateUser;
	this.login = login;
	this.register = register;
	this.url = "/api/user";
	this.loginUrl = "/api/login";
	this.registerUrl = "/api/register";
	var self = this;

	function login(username, password) {
		return fetch(self.loginUrl, {
			method: "post",
			body: JSON.stringify({username: username, password: password}),
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
			headers: {
				"content-type": "application/json"
			}
		});
	}
}