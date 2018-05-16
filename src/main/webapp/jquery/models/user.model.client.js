function User(username, password, email, firstName, lastName, phone, role, dob) {
	this.username = username;
	this.password = password;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.role = role;
	this.dob = dob;
	var self = this;


	this.setUsername = setUsername;
	this.getUsername = getUsername;
	this.setPassword = setPassword;
	this.getPassword = getPassword;
	this.setEmail = setEmail;
	this.getEmail = getEmail;
	this.setFirstName = setFirstName;
	this.getFirstName = getFirstName;
	this.setLastName = setLastName;
	this.getLastName = getLastName;
	this.setPhone = setPhone;
	this.getPhone = getPhone;
	this.setRole = setRole;
	this.getRole = getRole;
	this.setDOB = setDOB;
	this.getDOB = getDOB;
	
	function setUsername(username) {
		self.username = username;
	}
	function getUsername() {
		return self.username;
	}

	function setPassword(pass) {
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
