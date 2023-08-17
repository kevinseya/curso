import math
import numpy as np
import pandas as pd
from sklearn.tree import DecisionTreeRegressor


#TOCA GENERAR UNA FUNCION QUE INGRESE LOS DATOS QUE SE VAN A JALAR DE LA API
#modeloPrediccion(datosCaptados)
def modeloPrediccion(datosPrediccion):

# Datos de entrenamiento en formato .csv a raiz de la encuesta realizada
# La primera columna representa el género del evento, la segunda el número de asistentes,
# tercera el precio del ticket, el cuarto dia de la semana y el quinto la duración del evento
# para el día del evento se normalizó (1 para lunes, 2 para martes, etc.), y la quinta la duración del evento (en horas)
 dataEntrenamiento = 'nuevos_datos_generados.csv'
#columnas de la encuesta
 columnas = ['genero','asistentes','precioTicket','diaSemana','duracion']
#condiciones para leer el documento .csv
 data_entrenamiento = pd.read_csv(dataEntrenamiento, delimiter=';', usecols=columnas)


# Etiquetas de los datos de entrenamiento (cantidad de asistentes y precio del ticket)
 etiquetas_asistentes = data_entrenamiento['asistentes']
 etiquetas_precio = data_entrenamiento['precioTicket']
# Se codifica el genero de tal manera que cultural tome 0, academico 1, deportivo 2 y networking 3
 genero_codificados = data_entrenamiento['genero'].map({'cultural':0, 'academico':1, 'deportivo':2, 'networking':3})
# Se codifica el genero de tal manera que lunes 1, martes 2, miercoles 3 y asi sucesivamente
 diaSemana_codificados = data_entrenamiento['diaSemana'].map({'Lunes':1, 'Martes':2, 'Miércoles':3, 'Jueves':4, 'Viernes':5, 'Sábado':6, 'Domingo':7})
#se asigna la columna diaSemana codificado a la codificación antes realizada
 data_entrenamiento['diaSemana_codificado']=diaSemana_codificados
#se asigna la columna genero codificado a la codificación antes realizada
 data_entrenamiento['genero_codificado']=genero_codificados
#se crea el data de entrenamiento de entrada para los arboles, tomando en cuenta la columna codificada
 caracteristicas_entrenamiento = data_entrenamiento[['genero_codificado', 'duracion','diaSemana_codificado']]
# Creamos el modelo de Árbol de Decisión para generar el modelo en función de los asistentes
# y lo entrenamos con la etiqueta de los asistentes y las caracteristicas de entrada


 modelo_arbol_asistentes = DecisionTreeRegressor()
 modelo_arbol_asistentes.fit(caracteristicas_entrenamiento, etiquetas_asistentes)
# Creamos el modelo de Árbol de Decisión para generar el modelo en función del precio del ticket
# y lo entrenamos con la etiqueta de precio y las caracteristicas de entrada
 modelo_arbol_precio = DecisionTreeRegressor();
 modelo_arbol_precio.fit(caracteristicas_entrenamiento, etiquetas_precio)

#Aquí ingresarían los datos del usuario a predecir que seran captados por medio de una api al servidor de la logica de negocio
 data_prueba = pd.DataFrame({
        'genero': [''],
        'duracion': [0],
        'diaSemana': [0]
        # Evento académico el día 6 (sábado) con duración de 3 horas
 })
#GENERAMOS LOS VALORES QUE TOMAMOS DEL SISTEMA Y LOS NORMALIZAMOS
#tomamos de datosPrediccion que estamos generando la variable a traves
#del response.JSON, los valores genero, duracion y diaSemana
 genero = datosPrediccion['genero']
 duracion = datosPrediccion['duracion']
 diaSemana = datosPrediccion['diaSemana']
#Generamos el data frame que va ingresar al modelo
 data_prueba_sistema = pd.DataFrame({
        'genero': [genero],
        'duracion': [duracion],
        'diaSemana': [diaSemana]
 })
#Normalizacion de datos: genero
 data_prueba['genero'] = data_prueba_sistema['genero'].map({'Cultural':0, 'Académico':1, 'Deportivo':2, 'Networking':3})
#Normalizacion de datos día de la semana de acuerdo a la fecha
 data_prueba['diaSemana'] = pd.to_datetime(data_prueba_sistema['diaSemana']).dt.dayofweek+1
#Normalizacion de la hora 1:30 a 1.5 o 1 a 1
 data_prueba['duracion'] = data_prueba_sistema['duracion'].apply(transformar_duracion)

#se realiza la predicción con el modelo del arbol que previamente fue entrenado con salida de la predicción de asistentes
 prediccion_asistentes = modelo_arbol_asistentes.predict(data_prueba)
#aqui se redondea si en el caso de que tuvieramos valores decimales menores a 0.5 se queda con el numero entero
# y si son mayores a 0.5 se sube al siguiente numero entero
 prediccion_asistentes_redondeada = int(np.array([math.ceil(pred) if pred - int(pred) > 0.5 else int(pred) for pred in prediccion_asistentes]))
#se realiza la predicción con el modelo del arbol que previamente fue entrenado con salida de la predicción del precio del ticket
 prediccion_precio = modelo_arbol_precio.predict(data_prueba)

#Impresion final de los resultados arrojados por el modelo de predicción de árboles de decisión
 print("prediccion asistentes: ", prediccion_asistentes_redondeada)
 print("prediccion precioTicket: ", prediccion_precio)

 #CALCULO DE PRECISION MAE(Error absoluto medio error absoluto promedio entre predicciones y valores reales)
 errores_absolutos = [abs(prediccion_asistentes - valor_real) for valor_real in etiquetas_asistentes]
 mae_asistentes = sum(errores_absolutos) / len(errores_absolutos)
 print("MAE Asistentes:", mae_asistentes)

 errores_absolutos2 = [abs(prediccion_precio - valor_real) for valor_real in etiquetas_precio]
 mae_precio = sum(errores_absolutos2) / len(errores_absolutos2)
 print("MAE Precio:", mae_precio)


#en formato JSON para enviar al front a través de JavaScript
 resultados ={
  "prediccion_asistentes": prediccion_asistentes_redondeada,
  "prediccion_precio": float(prediccion_precio)
 }
 return resultados







#FUNCION PARA TRANSFORMAR DATOS DE DURACION INGRESADOS POR EL USUARIOCLIENTE
def transformar_duracion(duracion_str):
  partes = duracion_str.split(':')
  horas = int(partes[0])
  minutos = int(partes[1])
  duracion_decimal = horas+minutos / 60.0
  return duracion_decimal

