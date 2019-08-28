<template>
    <div>
        <v-app-bar 
        class="indigo darken-3" 
        dense
        fixed>
            <v-tooltip bottom>
                <template v-slot:activator="{on}">
                    <v-app-bar-nav-icon color="white"
                    :disabled="!statusLogado"
                    v-on="on"
                    @click="irParaMenu()">
                    </v-app-bar-nav-icon>
                </template>
                Menu
            </v-tooltip>

            <v-toolbar-title class="title" style="color: white">Carro de Aluguel</v-toolbar-title>

            <v-spacer></v-spacer>

           <v-tooltip bottom v-if="perfilUsuario != 'cliente'">
                <template v-slot:activator="{on}">
                    <v-btn 
                    text
                    class="indigo darken-3"
                    v-on="on"
                    to="/meusCarros"
                    :disabled="!statusLogado">
                        <v-icon color="white">mdi-car</v-icon>
                    </v-btn>
                </template>
                Meus carros
            </v-tooltip>

               <v-tooltip bottom v-if="perfilUsuario != 'cliente'">
                <template v-slot:activator="{on}">
                    <v-btn
                    text
                    class="indigo darken-3"
                    v-on="on"
                    to="/meusAlugueis"
                    :disabled="!statusLogado">
                        <v-icon color="white">mdi-car</v-icon>
                        <v-icon color="white">mdi-cash</v-icon>
                    </v-btn>
                </template>
                Mues carros alugados
            </v-tooltip>

            <v-tooltip bottom v-if="perfilUsuario != 'alugador'">
                <template v-slot:activator="{on}">
                    <v-btn
                    text
                    class="indigo darken-3"
                    to="/minhasLocacoes"
                    v-on="on    "
                    :disabled="!statusLogado">
                        <v-icon color="white">mdi-car</v-icon>
                        <v-icon color="white">mdi-account</v-icon>
                    </v-btn>
                </template>
                Minhas locações
            </v-tooltip> 

            <v-tooltip bottom>
                <template v-slot:activator="{on}">
                    <v-btn
                    text
                    class="indigo darken-3"
                    v-on="on"
                    to="/minhasInformacoes"
                    :disabled="!statusLogado">
                        <v-icon color="white">mdi-account</v-icon>
                    </v-btn>
                </template>
                Minhas informações
            </v-tooltip>
            
            <v-tooltip bottom>
                <template v-slot:activator="{on}">
                    <v-btn
                    :disabled="!statusLogado"
                    class="indigo darken-3"
                    text
                    v-on="on"
                    @click="logOut()">
                        <v-icon color="white">mdi-exit-to-app</v-icon>
                    </v-btn>
                </template>
                LogOut
            </v-tooltip>
            
            <v-btn 
            text
            :disabled="statusLogado"  
            style="color: white"    
            @click="goToLogin()">{{this.$store.state.usuarioLogado.nome || 'Login / Cadastro'}}
            </v-btn>
            
        </v-app-bar>
    </div>  
</template>

<script>
import { setTimeout } from 'timers';
export default {

    name: 'Toolbar',

    computed: {
        perfilUsuario(){
            return this.$store.state.usuarioLogado.perfil
        },

        statusLogado(){
            return this.$store.state.logado
        }
    },

    data(){
        return{
            categoriesMenu: false,
            menu: [
                {title:'Meus Carros', link:'/meusCarros'},
                {title:'Minhas Locações', link:'/minhasLocacoes'},
                {title:'Minhas Informações', link:'/minhasInformacoes' },
            ],
             defaultData: {
                nome: '',
                cpf: '',
                cnh: '',
                telefone: '',
                email: '',
                senha: '',
                perfil: '',
                endereco: {
                    logradouro: '',
                    bairro: '',
                    numero: '',
                    complemento: '',
                    cidade: '',
                    estado: '',
                }
            },
        }
    },

     methods:{
       goToLogin(){
           this.$router.push({path: '/'})
       },

       logOut(){
            setTimeout(() =>{
                this.$store.state.usuarioLogado = this.defaultData
                this.$store.state.logado = false
                this.$router.push({path: '/'})
            }, 1000)
        },

        irParaMenu(){
            if(this.$store.state.usuarioLogado.perfil == "cliente"){
                this.$router.push({path:'/cliente'})
            }else if(this.$store.state.usuarioLogado.perfil == "alugador"){
                this.$router.push({path:'/alugador'})
            }else{
                this.$router.push({path:'/misto'})
            }
        }
    }
}
</script>

<style>

</style>
