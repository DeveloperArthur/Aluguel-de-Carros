import CRUD from '@/components/crud/CRUD.js'

export default{
    todosCarrosParaAlugar(){
        return CRUD.lista('/carros')
    },

    todasMinhasLocacoes(){
        return CRUD.lista('/aluguel')
    },

    salva(locacao){
        const jsonCliente = JSON.stringify(locacao);
        return CRUD.novo('/aluguel', jsonCliente)
    },

    atualiza(aluguel){
        const jsonClienteTexto = {
            kmRodados: aluguel.kmRodados,
            docCarro: aluguel.docCarro,
            tipoCombustivel: aluguel.tipoCombustivel,
            marca: aluguel.marca,
            modelo: aluguel.modelo,
            placa: aluguel.placa,
            dataRet: aluguel.dataRet,
            dataEnt: aluguel.dataEnt,
        }
        const jsonCliente = JSON.stringify(jsonClienteTexto);
        return CRUD.atualiza('/aluguel/' + aluguel.id, jsonCliente)
    },
    
    apaga(aluguel){
        return CRUD.deleta('/aluguel', aluguel.id)
    }
}