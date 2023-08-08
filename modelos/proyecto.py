import numpy as np
from sklearn.tree import DecisionTreeRegressor

# Datos de entrenamiento
# Definimos el género del evento como 0 para musical, 1 para académico y 2 para artístico
# La primera columna representa el género del evento, la segunda el número de asistentes,
# la tercera el precio de entrada,
# la cuarta el día del evento (1 para lunes, 2 para martes, etc.), y la quinta la duración del evento (en horas)
data_entrenamiento = np.array([
    ["musical", 100, 50, 3, 2],
    ["académico", 50, 30, 5, 1.5],
    ["artístico", 200, 70, 2, 3],
    ["musical", 150, 60, 6, 2.5],
    ["académico", 80, 40, 4, 2],
    ["artístico", 120, 55, 7, 1],
    ["musical", 90, 45, 1, 2],
    ["académico", 70, 35, 5, 1],
    ["artístico", 180, 65, 2, 2.5]
])

# Etiquetas de los datos de entrenamiento (cantidad de asistentes)
etiquetas_asistentes = np.array([100, 50, 200, 150, 80, 120, 90, 70, 180])

# Codificación one-hot para el género del evento
generos = ["musical", "académico", "artístico"]
generos_codificados = np.zeros((len(data_entrenamiento), len(generos)), dtype=int)
for i, genero in enumerate(data_entrenamiento[:, 0]):
    generos_codificados[i, generos.index(genero)] = 5

# Combinamos las características codificadas con el resto de los datos de entrenamiento
data_entrenamiento_codificado = np.hstack((generos_codificados, data_entrenamiento[:, 1:]))

# Creamos el modelo de Árbol de Decisión para la cantidad de asistentes
modelo_arbol_asistentes = DecisionTreeRegressor()

# Entrenamos el modelo con los datos de entrenamiento para la cantidad de asistentes
modelo_arbol_asistentes.fit(data_entrenamiento_codificado, etiquetas_asistentes)

# Datos de prueba (género, día del evento, duración) para hacer predicciones
data_prueba = np.array([
    ["musical", 4, 1.5],  # Evento musical el día 4 (jueves) con duración de 1.5 horas
    ["artístico", 2, 2],  # Evento artístico el día 2 (martes) con duración de 2 horas
    ["académico", 6, 3],  # Evento académico el día 6 (sábado) con duración de 3 horas
])

# Codificación one-hot para el género del evento en los datos de prueba
generos_codificados_prueba = np.zeros((len(data_prueba), len(generos)), dtype=int)
for i, genero in enumerate(data_prueba[:, 0]):
    generos_codificados_prueba[i, generos.index(genero)] = 2

data_prueba_codificada = np.hstack((generos_codificados_prueba, np.zeros((len(data_prueba), 2)), data_prueba[:, 1:]))

# Realizamos las predicciones de cantidad de asistentes
predicciones_asistentes = modelo_arbol_asistentes.predict(data_prueba_codificada)

# Definir los parámetros para la recomendación del precio de entrada
precio_base = np.array([evento[1] for evento in data_prueba])  # Precios base de los eventos de prueba
incremento_por_asistente = 0.5  # Ajusta este valor según lo que consideres adecuado

# Imprimimos las predicciones de cantidad de asistentes y recomendación del precio de entrada
for i in range(len(predicciones_asistentes)):
    print(f" Predicción para el evento {i + 1}:")
    print(f" Cantidad de asistentes: {predicciones_asistentes[i]}")
    precio_base = data_prueba[i, 1]
    incremento_por_asistente = 0.2

    # Cálculo de la recomendación del precio de entrada basado en la cantidad de asistentes
    recomendacion_precio = float(precio_base) + (predicciones_asistentes * incremento_por_asistente)

    print(f"Recomendación del precio de entrada: {recomendacion_precio[i]}")

