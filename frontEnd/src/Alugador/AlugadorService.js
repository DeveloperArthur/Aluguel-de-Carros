import CRUD from '@/components/crud/CRUD.js'
import { store } from '../store.js'

export default{
    todos(){
        return CRUD.lista('/carro/usuario/' + store.state.usuarioLogado.id)
    },

    meusCarrosAlugados(){
        return CRUD.lista('/aluguel/usuario-registrador/' + store.state.usuarioLogado.id)
    },

    salva(carro){
        let carroFormatado = carro;
        carroFormatado.usuarioRegistradorId = store.state.usuarioLogado.id;
        const jsonCarro = JSON.stringify(carroFormatado);
        return CRUD.novo('/carro', jsonCarro)
    },

    atualiza(carro){
        const jsonCarroTexto = {
            id: carro.id,
            usuarioRegistradorId: store.state.usuarioLogado.id,
            kmsRodados: carro.kmsRodados,
            documentoCarro: carro.documentoCarro,
            tipoCombustivel: carro.tipoCombustivel,
            marca: carro.marca,
            modelo: carro.modelo,
            placa: carro.placa,
            valorCarro: carro.valorCarro,
        }
       const jsonCarro = JSON.stringify(jsonCarroTexto);
        return CRUD.atualiza('/carro', jsonCarro) 
    },
    
    apaga(carro){
        return CRUD.deleta('/carro', carro.id)
    }
}