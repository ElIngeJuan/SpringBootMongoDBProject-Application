from pymongo import MongoClient

client = MongoClient('mongodb://ISIS2304E35202320:nutubhmpkgXg@157.253.236.88:8087/ISIS2304E35202320')
db = client.ISIS2304E35202320

resultado_reservas = db.Reservas.delete_many({})
resultado_tipos_habitacion = db['Tipo de Habitaciones'].delete_many({})
resultado_usuarios = db.Usuarios.delete_many({})
resultado_servicios = db.Servicios.delete_many({})
