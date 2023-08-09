from flask import Flask, request, jsonify
from modeloPrediccion import modeloPrediccion
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/api/modeloPrediccion', methods=['POST'])
def recibirdatos():

        datosPrediccion = request.json
        resultado_modelo = modeloPrediccion(datosPrediccion)
        respuesta = {
            "valores de SISTEMA": datosPrediccion
        }
        print(respuesta)
        return jsonify({'Valores de sistema': datosPrediccion})




if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)