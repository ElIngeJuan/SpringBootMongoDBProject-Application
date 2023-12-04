from pymongo import MongoClient
import faker
import random
import datetime
import time
 
# Configura la semilla para faker
fake = faker.Faker()
fake.seed_instance(random.randint(0, 10000))

# Conéctate a MongoDB
client = MongoClient('mongodb://ISIS2304E35202320:nutubhmpkgXg@157.253.236.88:8087/ISIS2304E35202320')
db = client.ISIS2304E35202320

# Definir tamaños y otras variables
tamaños = { "Reservas": 200, "Servicios": 150, "Habitaciones": 150, "TiposHabitacion": 20}
reservas = []
fechasHabitaciones = {}

# Definir tamaños y otras variablSes

servicios = []

# Lista de tipos de servicios
tipos_servicios = [
    'Piscina', 'Bar', 'Internet', 'Gimnasio', 'Restaurante', 'Supermercado', 'Tienda', 'SPA',
    'Lavanderia', 'Utensilios', 'Sala de conferencias', 'Sala de reuniones'
]

nombres_playas = ['Playa del Carmen', 'Copacabana', 'Waikiki', 'Bondi Beach', 'Ipanema', 'Malibu', 'Cancún', 'Boracay']


nombres_gimnasios = ['Gold\'s Gym', 'Anytime Fitness', 'Planet Fitness', '24 Hour Fitness', 'CrossFit', 'Equinox']

nombres_estilos_musica = ['Pop', 'Rock', 'Jazz', 'R&B', 'Electrónica', 'Reggae', 'Clásica', 'Hip-hop']


nombres_utensilios = ['Cuchara', 'Tenedor', 'Cuchillo', 'Sartén', 'Olla', 'Espátula', 'Batidor', 'Colador']

nombres_ropa = ['Camisa', 'Pantalón', 'Vestido', 'Calcetines', 'Camiseta', 'Falda', 'Abrigo', 'Bufanda']


nombres_productos = ['Shampoo', 'Acondicionador', 'Jabón', 'Cepillo de dientes', 'Pasta dental', 'Toallas', 'Papel higiénico', 'Cereal']

nombres_tiendas = ['Fashion Outlet', 'Gadget Haven', 'Book Nook', 'Sports Emporium', 'Home Essentials']

nombres_supermercados = ['Fresh Mart', 'Quick Shop', 'Market Basket', 'MegaMart', 'Grocery Palace']
# Generar servicios
for i in range(tamaños["Servicios"]):
    tipo_servicio = random.choice(tipos_servicios)
    servicio = {'nombre': f'{tipo_servicio} {i + 1}', 'tipo': tipo_servicio}

    if tipo_servicio == 'Piscina':
        servicio['nombre'] = f'{random.choice(nombres_playas)} Pool {i + 1}'
        servicio['capacidad'] = random.randint(10, 100)
        servicio['profundidad'] = random.randint(1, 3)
        servicio['horarioApertura'] = fake.time()
        servicio['horarioCierre'] = fake.time()
    elif tipo_servicio == 'Gimnasio':
        servicio['nombre'] = f'{random.choice(nombres_gimnasios)} Gym {i + 1}'
        servicio['capacidad'] = random.randint(5, 50)
        servicio['maquinas'] = [fake.word() for _ in range(random.randint(5, 15))]
        servicio['horarioApertura'] = fake.time()
        servicio['horarioCierre'] = fake.time()
    elif tipo_servicio == 'Internet':
        servicio['capacidad'] = random.randint(50, 1000)
    elif tipo_servicio in ['Bar', 'Restaurante']:
        servicio['capacidad'] = random.randint(10, 100)
        servicio['estiloMusica'] = random.choice(nombres_estilos_musica)
        servicio['listaProductos'] = [random.choice(nombres_productos) for _ in range(random.randint(5, 15))]
    elif tipo_servicio == 'Supermercado':
        servicio['nombre'] = f'{random.choice(nombres_supermercados)} Market {i + 1}'
        servicio['listaProductos'] = [random.choice(nombres_productos) for _ in range(random.randint(10, 30))]
    elif tipo_servicio == 'Tienda':
        servicio['nombre'] = f'{random.choice(nombres_tiendas)} Store {i + 1}'
        servicio['tipoTienda'] = random.choice(['productos', 'joyería', 'ropa', 'electrónica', 'juguetes'])
        servicio['listaProductos'] = [random.choice(nombres_productos) for _ in range(random.randint(5, 15))]
    elif tipo_servicio == 'SPA':
        servicio['duracion'] = f'{random.randint(30, 120)}'
    elif tipo_servicio == 'Lavanderia':
        servicio['nombre'] = f'{random.choice(nombres_ropa)} Laundry {i + 1}'
        servicio['numeroPrendas'] = random.randint(1, 20)
    elif tipo_servicio == 'Utensilios':
        servicio['nombre'] = f'{random.choice(nombres_utensilios)} {fake.first_name()}'
    elif tipo_servicio == 'Sala de conferencias':
        servicio['nombre'] = f'Salon {fake.first_name()} {fake.last_name()}'
        servicio['capacidad'] = random.randint(10, 100)
        servicio['equipos'] = fake.word()
        servicio['horarioApertura'] = fake.time()
        servicio['horarioCierre'] = fake.time()
    elif tipo_servicio == 'Sala de reuniones':
        servicio['nombre'] = f'Salon {fake.first_name()} {fake.last_name()}'
        servicio['capacidad'] = random.randint(10, 20)
        servicio['equipos'] = fake.word()
        servicio['horarioApertura'] = fake.time()
        servicio['horarioCierre'] = fake.time()

    servicios.append(servicio)



# Generar reservas y agregar consumos
reservas = []

for i in range(tamaños["Reservas"]):
    reserva = {
        '_id': i + 1,
        'habitacion': random.randint(1, 100),
        'usuario_presencia': random.randint(10000000, 99999999),
        'fecha_entrada': fake.date_this_year().strftime('%Y-%m-%d'),
        'fecha_salida': fake.date_this_year().strftime('%Y-%m-%d'),
        'num_noches': random.randint(1, 7),
        'num_personas': random.randint(1, 4),
        'consumos': []
    }

    # Agregar consumos de servicios a la reserva
    for servicio in random.sample(servicios, random.randint(1, 5)):  # Se eligen servicios aleatorios
        consumo = {
            'servicio': servicio['nombre'],
            'fecha': fake.date_this_year().strftime('%Y-%m-%d'),
            'valor': random.randint(100000, 700000)
        }
        reserva['consumos'].append(consumo)

    reservas.append(reserva)

# Insertar servicios en MongoDB
result_servicios = db.Servicios.insert_many(servicios)

# Insertar reservas en MongoDB
result_reservas = db.Reservas.insert_many(reservas)

# Generar tipos de habitación
tipos_habitacion = []

# Lista de posibles tipos de habitación
nombres_tipos_habitacion = ["Sencilla", "Doble", "Suite", "Presidencial", "Familiar"]

# Lista de posible dotación de habitaciones
posibles_dotaciones = ["Televisión", "Minibar", "Aire acondicionado", "Wi-Fi", "Balcón"]
# Lista para evitar repeticiones de IDs de habitaciones
used_ids = set()

for i in range(tamaños["TiposHabitacion"]):
    tipo_habitacion = {
        '_id': i + 1,
        'tipo_habitacion': random.choice(nombres_tipos_habitacion),
        'capacidad': random.randint(1, 4),
        'dotacion': ",".join(random.sample(posibles_dotaciones, random.randint(1, len(posibles_dotaciones)))),
        'habitaciones': []
    }

    for j in range(random.randint(5, 10)):
        # Asegurarse de que el ID de la habitación no se repita
        id_habitacion = None
        while id_habitacion is None or id_habitacion in used_ids:
            id_habitacion = random.randint(1, tamaños["Habitaciones"])
        used_ids.add(id_habitacion)

        habitacion = {
            '_id': id_habitacion,
            'valor_noche': random.randint(250000, 1500000)
        }

        tipo_habitacion['habitaciones'].append(habitacion)

    tipos_habitacion.append(tipo_habitacion)

# Insertar tipos de habitación en MongoDB
result_tipos_habitacion = db["Tipo de Habitaciones"].insert_many(tipos_habitacion)

# Generar usuarios
check_ins = []

for reserva in reservas:
    check_in_realizado = random.choice([0, 1])
    check_out_realizado = random.choice([0, 1])
    check_in = {
        'documento': reserva['usuario_presencia'],
        'reserva': reserva['_id'],
        'check_in': check_in_realizado,
        'check_out': check_out_realizado
    }
    check_ins.append(check_in)

# Insertar check-ins en MongoDB
result_check_ins = db.Usuarios.insert_many(check_ins)



# Cerrar la conexión a MongoDB
client.close()
