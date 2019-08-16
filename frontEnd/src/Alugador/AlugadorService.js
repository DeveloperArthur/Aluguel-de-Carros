import CRUD from '@/components/crud/CRUD.js'

export default{
    todos(){
        return CRUD.lista('/carros')
    },

    salva(carro){
        const jsonCliente = JSON.stringify(carro);
        return CRUD.novo('/carros', jsonCliente)
    },

    atualiza(carro){
        const jsonClienteTexto = {
            kmRodados: carro.kmRodados,
            docCarro: carro.docCarro,
            tipoCombustivel: carro.tipoCombustivel,
            marca: carro.marca,
            modelo: carro.modelo,
            placa: carro.placa,
        }
        const jsonCliente = JSON.stringify(jsonClienteTexto);
        return CRUD.atualiza('/carros/' + carro.id , jsonCliente)
    },
    
    apaga(carro){
        return CRUD.deleta('/carros', carro.id)
    }
}