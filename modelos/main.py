from flask import Flask, request, jsonify
from modeloPrediccion import modeloPrediccion

app = Flask(__name__)

@app.post('api/modelo')
def api_modelo():
    try:
        datosCaptados = request.json
        resultado = modeloPrediccion(datosCaptados)
        return jsonify({'resultado':resultado})
    except Exception as e:
        return jsonify({'error':str(e)})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)