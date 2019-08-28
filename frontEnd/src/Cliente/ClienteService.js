import CRUD from '@/components/crud/CRUD.js'
import {store} from '../store.js'

export default{
    todosCarrosParaAlugar(){
        return CRUD.lista('/carro')
    },

    todasMinhasLocacoes(){
        return CRUD.lista('/aluguel/usuario/' + store.state.usuarioLogado.id)
    },

    salva(locacao){
        let locacaoFormatada = locacao;
        locacaoFormatada.usuarioClienteId = store.state.usuarioLogado.id
        const jsonAluguel = JSON.stringify(locacaoFormatada);
        return CRUD.novo('/aluguel', jsonAluguel)
    },

    atualiza(locacao){  
        const jsonLocacaoTexto = {
            id: locacao.id,            
            retirada: locacao.retirada,
            entrega: locacao.entrega,
            valor: locacao.valor,
            carro:  locacao.carro,
            usuarioCliente: locacao.usuarioCliente
        }
        const jsonLocacao = JSON.stringify(jsonLocacaoTexto);
        return CRUD.atualiza('/aluguel', jsonLocacao) 
    },
    
    apaga(aluguel){
        return CRUD.deleta('/aluguel', aluguel.id)
    }
}