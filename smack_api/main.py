"""`main` is the top level module for your Flask application."""

# Import the Flask Framework
from flask import Flask
from application import db
from application.models import Data, Rack

app = Flask(__name__)
# Note: We don't need to call run() since our application is embedded within
# the App Engine WSGI application server.


@app.route('/')
def hello():
    """Return a friendly HTTP greeting."""
    return 'Hello World!'

@app.route('/updateSlot/<int:rack_num>/<int:slot_num>/<new_value>', methods=['GET', 'POST'])
def updateSlot(rack_num, slot_num, new_value):
    print("Updating rack " + str(rack_num) + " slot " + str(slot_num) + " with " + str(new_value))
    if slot_num == 1:
        try: 
            #r = Rack(new_value)    
            r = Rack.query.get(rack_num)
            #r = db.session.query(Rack).get(1)
            print("rack " + str(r.id) + " slot 1 status " + str(r.slot1))
            r.slot1 = new_value
            db.session.commit()        
            db.session.close()
        except:
            db.session.rollback()
            return "update unsuccessful"
    elif slot_num == 2:
        try: 
            #r = Rack(new_value)    
            r = Rack.query.get(rack_num)
            #r = db.session.query(Rack).get(1)
            print("rack " + str(r.id) + " slot 2 status " + str(r.slot2))
            r.slot2 = new_value
            db.session.commit()        
            db.session.close()
        except:
            db.session.rollback()
            return "update unsuccessful"
    return "update successful"

@app.route('/getSlot/<int:rack_num>/<int:slot_num>/', methods=['GET', 'POST'])
def getSlot(rack_num, slot_num):
    print("Getting rack " + str(rack_num) + " slot " + str(slot_num) + " status ")
    if slot_num == 1:
        try: 
            #r = Rack(new_value)    
            r = Rack.query.get(rack_num)
            #r = db.session.query(Rack).get(1)
            print("rack " + str(r.id) + " slot 1 status " + str(r.slot1))
            return str(r.slot1)
        except:
            db.session.rollback()
            return "get slot unsuccessful"
    elif slot_num == 2:
        try: 
            #r = Rack(new_value)    
            r = Rack.query.get(rack_num)
            #r = db.session.query(Rack).get(1)
            print("rack " + str(r.id) + " slot 2 status " + str(r.slot2))
            return str(r.slot2)
        except:
            db.session.rollback()
            return "get slot unsuccessful"
    return "get slot num not 1 2"

@app.errorhandler(404)
def page_not_found(e):
    """Return a custom 404 error."""
    return 'Sorry, Nothing at this URL.', 404


@app.errorhandler(500)
def application_error(e):
    """Return a custom 500 error."""
    return 'Sorry, unexpected error: {}'.format(e), 500
