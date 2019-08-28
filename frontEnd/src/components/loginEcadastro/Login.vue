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

                            <v-snackbar 
                            top
                            color="error"
                            v-model="snackbarErroLogin"
                            :timeout="2000">
                            Email ou senha incorreto
                                <v-icon color="white">mdi-close-circle</v-icon>
                            </v-snackbar>
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
            snackbarErroLogin: false,
            mostrarSenha: false,
            loadingLogar: false,
            loadingParaCriarNovaConta: false,
            login: {
                email: '',
                senha: ''
            },
        }
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
            service.logar(this.login)
            .then(resposta => {
                console.log(resposta)
                this.loadingLogar = false
                this.$store.state.usuarioLogado = resposta.data;
                this.$store.state.logado = true
                this.verificaPerfilDoUsuarioLogado()
            }).catch(erro => {
                console.log(erro)
                if(erro.request.status == 404){
                   this.snackbarErroLogin = true
                }
                setTimeout(()=> {
                    this.snackbarErroLogin = false
                    this.loadingLogar = false
                },1500) 
            })
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
    }    
}
</script>

<style>

</style>
