import numpy as np
import pandas as pd

# Cargar el conjunto de datos original
data = pd.read_csv('dataSetEntrenamiento.csv', delimiter=';')

# Número de datos a generar
num_samples = 5000

# Generar datos mezclados y ligeramente ajustados
generated_data = []

for _ in range(num_samples):
    sample = data.sample()  # Tomar una muestra aleatoria del conjunto de datos original
    perturbed_sample = sample.copy()

    # Aplicar pequeñas perturbaciones a las características
    perturbed_sample['asistentes'] += np.random.randint(-10, 10)  # Ejemplo de perturbación en asistentes
    perturbed_sample['precioTicket'] *= np.random.uniform(0.95, 1.05)  # Ejemplo de perturbación en precioTicket

    generated_data.append(perturbed_sample)

# Crear un DataFrame con los datos generados
generated_df = pd.concat(generated_data, ignore_index=True)

# Guardar los nuevos datos en un archivo CSV
generated_df.to_csv('nuevos_datos_generados5000.csv', index=False, sep=';')