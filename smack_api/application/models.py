from application import db

class Data(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	notes = db.Column(db.String(128), index=True, unique=False)
	
	def __init__(self, notes):
		self.notes = notes

	def __repr__(self):
		return '<Data %r>' % self.notes

class Rack(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	slot1 = db.Column(db.Integer, nullable=False)
	slot2 = db.Column(db.Integer, nullable=False)

	def __init__(self, slot1, slot2):
		self.slot1 = slot1
		self.slot2 = slot2

	def __repr__(self):
		return '<Slot1 %r>' % self.slot1

class User(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	email = db.Column(db.String(128), unique=True)
	password = db.Column(db.String(128))

	def __init__(self, email, password):
		self.email = email
		self.password = password
