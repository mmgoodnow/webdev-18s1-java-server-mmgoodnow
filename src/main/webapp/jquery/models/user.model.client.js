function User(json) {
	var self = this;

	self.setID = setID;
	self.getID = getID;
	self.setUsername = setUsername;
	self.getUsername = getUsername;
	self.setPassword = setPassword;
	self.getPassword = getPassword;
	self.setEmail = setEmail;
	self.getEmail = getEmail;
	self.setFirstName = setFirstName;
	self.getFirstName = getFirstName;
	self.setLastName = setLastName;
	self.getLastName = getLastName;
	self.setPhone = setPhone;
	self.getPhone = getPhone;
	self.setRole = setRole;
	self.getRole = getRole;
	self.setDOB = setDOB;
	self.getDOB = getDOB;

	if (json.hasOwnProperty('id')) self.setID(json.id);
	if (json.hasOwnProperty('username')) self.setUsername(json.username);
	if (json.hasOwnProperty('password')) self.setPassword(json.password);
	if (json.hasOwnProperty('email')) self.setEmail(json.email);
	if (json.hasOwnProperty('firstName')) self.setFirstName(json.firstName);
	if (json.hasOwnProperty('lastName')) self.setLastName(json.lastName);
	if (json.hasOwnProperty('phone')) self.setPhone(json.phone);
	if (json.hasOwnProperty('role')) self.setRole(json.role);
	if (json.hasOwnProperty('dob')) self.setDOB(json.dob);

	function setID(id) {
		self.id = id;
	}
	function getID() {
		return self.id;
	}
	function setUsername(username) {
		self.username = username;
	}
	function getUsername() {
		return self.username;
	}

	function setPassword(password) {
		self.password = password;
	}
	function getPassword() {
		return self.password;
	}
	
	function setEmail(email) {
		self.email = email;
	}
	function getEmail() {
		return self.email;
	}
	
	function setFirstName(first) {
		self.firstName = first;
	}
	function getFirstName() {
		return self.firstName;
	}
	
	function setLastName(last) {
		self.lastName = last;
	}
	function getLastName() {
		return self.lastName;
	}
	
	function setPhone(phone) {
		self.phone = phone.replace(/\D/g,'');
	}
	function getPhone() {
		return self.phone;
	}

	function setRole(role) {
		self.role = role;
	}
	function getRole() {
		return self.role;
	}

	function setDOB(dob) {
		self.dob = dob;
	}
	function getDOB() {
		return self.dob;
	}
	

}
