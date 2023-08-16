import pandas as pd
import requests
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel


#Crear un DataFrame con títulos de eventos y sus etiquetas
"""
data = {
    'titulo_evento': [
        'Conferencia de Ciencia de Datos',
        'Taller de Desarrollo Web',
        'Feria de Tecnología',
        'Seminario de Marketing Digital',
        'Charla sobre Inteligencia Artificial',
        'Hackathon de Innovación',
        'Exposición de Realidad Virtual',
        'Curso de Machine Learning',
        'Conferencia de Startups',
        'Seminario de Blockchain',
        'Festival de Jazz en el Parque',
        'Concierto de Rock Alternativo',
        'Noche de Música Clásica',
        'Fiesta de Electrónica en Vivo',
        'Concierto de Hip-Hop y Rap',
        'Tarde de Reggae y Ska',
        'Festival de Música Indie',
        'Gala de Ópera y Orquesta',
        'Concierto de Pop Latino',
        'Rave de Música House',
        'Exposición de Pintura Clásica',
        'Feria de Arte Contemporáneo',
        'Galería de Esculturas en Bronce',
        'Noche de Fotografía Creativa',
        'Festival de Arte Urbano',
        'Exposición de Arte Aborigen',
        'Taller de Pintura al Aire Libre',
        'Subasta de Arte Moderno',
        'Galería de Arte Digital',
        'Exposición de Arte Surrealista',
        'Concierto de Música Electrónica en 3D',
        'Festival de Música y Realidad Virtual',
        'Taller de Creación de Instrumentos Digitales',
        'Conferencia de Música y Tecnología',
        'Exposición de Música Generativa',
        'Concierto de Orquesta Sinfónica Digital',
        'Hackathon de Música y Programación',
        'Tarde de DJ con Set de Vinilos Digitales',
        'Taller de Producción Musical con Inteligencia Artificial',
        'Fiesta de Música Indie y Realidad Aumentada',
        'Carrera de 5K con Música en Realidad Virtual',
        'Torneo de E-Sports con DJ en Vivo',
        'Partido de Baloncesto con Iluminación LED Sincrónica',
        'Maratón con Entrenamiento Guiado por Auriculares',
        'Competencia de Ciclismo en Realidad Aumentada',
        'Partido de Fútbol con Reproductores de Música Integrados',
        'Torneo de Golf con Tecnología de Análisis de Swing',
        'Carrera de Drones al Ritmo de la Música',
        'Partido de Tenis con Bolas Lumínicas Interactivas',
        'Campeonato de Surf con Playlist Personalizada',
        'Carrera de Obstáculos con Bandas Sonoras Interactivas',
        'Torneo de Baloncesto en Realidad Aumentada',
        'Competencia de Atletismo con Dispositivos Wearables',
        'Partido de Fútbol con Celebraciones Musicales',
        'Torneo de Golf con Simuladores de Campo',
        'Carrera de Bicicletas Eléctricas con DJ en Ruta',
        'Partido de Tenis de Mesa con Luces Estroboscópicas',
        'Campeonato de Natación con Ritmos Subacuáticos',
        'Evento de Crossfit con Entrenamiento Guiado por Auriculares',
        'Maratón de Baile Fitness con Juegos de Luces',
        'Carrera de Drones en Circuito 3D',
        'Torneo de E-Sports en Estadio Virtual',
        'Competencia de Ciclismo en Realidad Virtual',
        'Partido de Fútbol con Balón Inteligente',
        'Maratón de Realidad Aumentada',
        'Torneo de Golf con Simuladores de Swing',
        'Carrera de Autos Eléctricos de Alta Tecnología',
        'Torneo de Ajedrez en Línea con IA',
        'Campeonato de Carreras Virtuales',
        'Partido de Tenis con Raquetas Conectadas'
    ],
    'etiquetas': [
        'ciencia de datos|nalítica|machine learning|IA',
        'desarrollo web|front-end|back-end|diseño',
        'tecnología|innovación|startups|robótica',
        'marketing digital|publicidad en línea|SEO',
        'inteligencia artificial|aprendizaje automático|procesamiento de lenguaje natural',
        'innovación|programación|equipo|creatividad',
        'realidad virtual|tecnología|experiencia inmersiva',
        'machine learning|algoritmos|predicción|datos',
        'startups|emprendimiento|inversión|crecimiento',
        'blockchain|criptomonedas|tecnología distribuida|seguridad',
        'jazz|festival|música en vivo|eventos al aire libre',
        'rock alternativo|concierto|bandas locales|música en vivo',
        'música clásica|concierto sinfónico|instrumentos|cultura',
        'electrónica|fiesta en vivo|DJ|música de baile',
        'hip-hop|rap|concierto urbano|música en vivo',
        'reggae|ska|fiesta temática|ritmos caribeños',
        'indie|festival|bandas emergentes|música en vivo',
        'ópera|orquesta|concierto formal|interpretación vocal',
        'pop latino|concierto bailable|artistas populares|música en vivo',
        'house|rave|música electrónica|ambiente festivo',
        'arte|pintura clásica|exposición|obras maestras',
        'arte contemporáneo|feria de arte|artistas emergentes|tendencias',
        'esculturas en bronce|galería|arte tridimensional|artistas locales',
        'fotografía creativa|exposición|imágenes impactantes|experimentación',
        'arte urbano|festival|murales|cultura callejera',
        'arte aborigen|exposición|culturas tradicionales|historia',
        'pintura al aire libre|taller creativo|naturaleza|materiales',
        'arte moderno|subasta|obras contemporáneas|inversión',
        'arte digital|galería en línea|obras interactivas|tecnología',
        'arte surrealista|exposición|imaginación|simbolismo',
        'música electrónica|concierto 3D|tecnología de sonido|música en vivo',
        'música|realidad virtual|experiencia inmersiva|tecnología audiovisual',
        'creación de instrumentos digitales|taller|tecnología musical|creatividad',
        'música|tecnología|conferencia|innovación en música',
        'música generativa|exposición|algoritmos creativos|arte digital',
        'orquesta sinfónica digital|concierto virtual|tecnología de interpretación|música clásica',
        'hackathon|música|programación|desarrollo de aplicaciones musicales',
        'DJ|vinilos digitales|música electrónica|música en vivo',
        'producción musical|IA|tecnología en estudio|música experimental',
        'música indie|realidad aumentada|fiesta temática|tecnología interactiva',
        'carrera|5K|realidad virtual|música motivadora|tecnología deportiva',
        'e-sports|torneo|DJ en vivo|competencia virtual|música electrónica',
        'baloncesto|iluminación LED|espectáculo visual|tecnología de estadio|música sincronizada',
        'maratón|entrenamiento guiado|auriculares|tecnología de fitness|música en movimiento',
        'ciclismo|realidad aumentada|competencia virtual|recorridos interactivos|música motivadora',
        'fútbol|reproductores de música|tecnología integrada|ambiente de juego|música durante el partido',
        'golf|análisis de swing|tecnología de entrenamiento|precisión|música relajante',
        'drones|carrera|tecnología de vuelo|música al ritmo|competencia aérea',
        'tenis|bolas lumínicas|interacción visual|tecnología de juego|música en la cancha',
        'surf|campeonato|playlist personalizada|olas|tecnología acuática',
        'carrera de obstáculos|música interactiva|desafío físico|tecnología en recorrido|música durante la carrera',
        'baloncesto|realidad aumentada|torneo virtual|competencia inmersiva|música en cancha',
        'atletismo|dispositivos wearables|tecnología de seguimiento|medición de rendimiento|música motivadora',
        'fútbol|celebraciones musicales|ambiente festivo|tecnología en partido|música en los goles',
        'golf|simuladores|campo virtual|tecnología de precisión|música relajante',
        'bicicletas eléctricas|DJ en ruta|experiencia ciclística|música durante el recorrido|deporte y música',
        'tenis de mesa|luces estroboscópicas|partido emocionante|tecnología visual|música durante el juego',
        'natación|ritmos subacuáticos|campeonato acuático|experiencia única|música bajo el agua',
        'crossfit|auriculares|entrenamiento guiado|tecnología fitness|música motivadora',
        'baile fitness|maratón|luces de discoteca|entrenamiento divertido|música bailable',
        'carrera de drones|circuito 3D|tecnología de vuelo|competencia aérea|máquinas voladoras',
        'e-sports|torneo|estadio virtual|competencia en línea|videojuegos',
        'ciclismo|realidad virtual|recorridos interactivos|tecnología de entrenamiento|experiencia inmersiva',
        'fútbol|balón inteligente|tecnología deportiva|partido tecnológico|interacción digital',
        'maratón|realidad aumentada|recorrido virtual|tecnología fitness|mundo digital',
        'golf|simuladores de swing|entrenamiento de precisión|tecnología en campo|máquinas de golf',
        'autos eléctricos|alta tecnología|carrera futurista|innovación automotriz|competencia rápida',
        'ajedrez en línea|torneo|inteligencia artificial|estrategia digital|juego mental',
        'carreras virtuales|competiciones en línea|entornos digitales|experiencia en red|mundo virtual',
        'tenis|raquetas conectadas|tecnología en deporte|partido interactivo|juego digital'
    ]
}
"""
def modeloRecomendacion(nombre_evento):
#Captura de datos del backend para analizar

 url = "http://localhost:8080/api/eventosModelo"
 response = requests.get(url)

 if response.status_code == 200:
     eventos_data = response.json()

     data = {'titulo_evento': [], 'etiquetas': []}

     for evento in eventos_data:
        titulo = evento['nombre']
        etiqueta = evento['etiqueta']

        data['titulo_evento'].append(titulo)
        data['etiquetas'].append(etiqueta)





 df_eventos = pd.DataFrame(data)

# Imprime el DataFrame de eventos
 print("DataFrame de Eventos:")
 print(df_eventos)
 print()

 from sklearn.feature_extraction.text import TfidfVectorizer
 tfidf = TfidfVectorizer(stop_words='english')

# Divide las etiquetas en listas individuales y crea una lista de etiquetas únicas
 etiquetas_split = df_eventos['etiquetas'].apply(lambda x: x.split(','))
 etiquetas_unique = list(set([item for sublist in etiquetas_split for item in sublist]))

# Reemplaza el separador '|' con un espacio en blanco para cada fila en las etiquetas
 df_eventos['etiquetas'] = df_eventos['etiquetas'].apply(lambda x: ' '.join(x.split(',')))

# Crea la matriz TF-IDF a partir de las etiquetas
 tfidf_matrix = tfidf.fit_transform(df_eventos['etiquetas'])

# Imprime la longitud del vocabulario TF-IDF y la forma de la matriz
#Transformar nombre del evento en vector, lanza a funcion de similitud de cosenos
 print("Longitud del vocabulario TF-IDF:", len(tfidf.vocabulary_))
 print("Forma de la matriz TF-IDF:", tfidf_matrix.shape)

#similitudes de cosenos
 from sklearn.metrics.pairwise import linear_kernel
 cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

# Crea un índice con los títulos de eventos para la búsqueda rápida
 indices = pd.Series(df_eventos.index, index=df_eventos['titulo_evento']).drop_duplicates()

 def get_recommendations(title, cosine_sim=cosine_sim):
     idx = indices[title]
     sim_scores = list(enumerate(cosine_sim[idx]))
     sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
     sim_scores = sim_scores[1:5]
     event_indices = [i[0] for i in sim_scores]
     return df_eventos['titulo_evento'].iloc[event_indices]

# Título del evento seleccionado (puede cambiarse por otro título del DataFrame)
 titulo_evento = str(nombre_evento)

# Imprime el evento seleccionado y sus recomendaciones
 print("Tu seleccionaste:", titulo_evento, "y tus recomendaciones son:")
 print(get_recommendations(titulo_evento))
 return (get_recommendations(titulo_evento).tolist())