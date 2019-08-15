<template>
    <div>
        <v-container style="width: 600px; margin-top: 100px">
            <v-card>
                <v-card-title class="elevation-3 indigo darken-3"
                style="color: white"> Login </v-card-title>
                <v-card-text>
                    <v-flex xs8>
                        <v-text-field
                        v-model="login.email"
                        label="E-mail:">
                        </v-text-field>
                    </v-flex>

                    <v-flex xs6>
                        <v-text-field
                        v-model="login.senha"
                        label="Senha:"
                        :append-icon="mostrarSenha ? 'mdi-eye' : 'mdi-eye-off'"
                        @click:append="mostrarSenha = !mostrarSenha"
                        :type="mostrarSenha ? 'text' : 'password'">
                        </v-text-field>
                    </v-flex>
                    
                    <v-layout row wrap>
                        <v-flex xs9>
                            <v-btn
                            text
                            :loading="loadingParaCriarNovaConta" 
                            style="margin-left: 10px" 
                            color="indigo darken-3"
                            @click="irParaPaginaNovaConta()">Criar Conta</v-btn>
                        </v-flex>
                        <v-flex xs3>
                            <v-btn 
                            class="indigo darken-3"
                            :loading="loadingLogar"
                            style="margin-left: 25px; color: white;font-size: 13px"
                            @click="logar()"> Entrar </v-btn>
                        </v-flex>
                    </v-layout>
                </v-card-text>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import { setTimeout } from 'timers';
import service from './CadastroELoginService.js';

export default {
    
    data(){
        return{
            usuariosCadastrados: [],
            mostrarSenha: false,
            loadingLogar: false,
            loadingParaCriarNovaConta: false,
            
            login: {
                email: '',
                senha: ''
            },
        }
    },

    created(){
        this.pegarUsuariosCadastrados()
    },
    
    methods: {

        irParaPaginaNovaConta(){
           this.loadingParaCriarNovaConta = true
           setTimeout(() =>{
               this.loadingParaCriarNovaConta = false
               this.$router.push({path:'/cadastro'})
           },1500)
        },

        logar(){
            this.loadingLogar = true   
            for(let i = 0; i < this.usuariosCadastrados.length; i++){
                if(this.login.email == this.usuariosCadastrados[i].email 
                 && this.login.senha == this.usuariosCadastrados[i].senha){
                     setTimeout(() =>{
                        this.$store.state.usuarioLogado = this.usuariosCadastrados[i]
                        this.$store.state.logado = true
                        this.loadingLogar = false
                        this.verificaPerfilDoUsuarioLogado()
                    }, 1500)
                }
            }
        },

        verificaPerfilDoUsuarioLogado(){
            if(this.$store.state.usuarioLogado.perfil == 'cliente'){
                this.$router.push({path: '/cliente'})
            }else if(this.$store.state.usuarioLogado.perfil == 'alugador'){
                this.$router.push({path: '/alugador'})
            }else{
                this.$router.push({path: '/misto'})
            }
        },

        pegarUsuariosCadastrados(){
            service.todos()
            .then(resposta => {
                console.log(resposta)
                this.usuariosCadastrados = resposta.data
            }).catch(erro => console.log(erro))
        },
    }    
}
</script>

<style>

</style>
