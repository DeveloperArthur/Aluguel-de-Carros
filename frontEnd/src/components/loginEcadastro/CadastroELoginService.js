import CRUD from '@/components/crud/CRUD.js'

export default{
    todos(){
        return CRUD.lista('/usuarios')
    },
    
    salva(usuarios){
        const jsonCliente = JSON.stringify(usuarios);
        return CRUD.novo('/usuarios', jsonCliente)
    },
    
    atualiza(usuario){
        const jsonClienteTexto = {
            nome: usuario.nome,
            cpf:  usuario.cpf  ,
            cnh: usuario.cnh,
            telefone: usuario.telefone,
            email: usuario.email,
            senha: usuario.senha,
            perfil: usuario.perfil,
            endereco: {
                logradouro: usuario.endereco.logradouro,
                bairro: usuario.endereco.bairro,
                numero: usuario.endereco.numero,
                complemento: usuario.endereco.complemento,
                cidade: usuario.endereco.cidade,
                estado: usuario.endereco.estado,
            }        
        }
        const jsonCliente = JSON.stringify(jsonClienteTexto);
        return CRUD.atualiza('/usuarios/' + usuario.id , jsonCliente) 
    },
}