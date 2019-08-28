<template>
    <div>
        <v-container style="margin-top: 60px">            
            <v-layout row wrap>
                <v-flex xs6>
                    <v-container>
                        <v-card>
                            <v-card-title class="elevation-2 light-blue"
                            style="color:white">Dados Residencias
                                <v-spacer></v-spacer> 
                                <v-btn 
                                text
                                @click="dadosResidenciaisEditaveis = !dadosResidenciaisEditaveis">
                                    <v-icon color="white">mdi-border-color</v-icon>
                                </v-btn>
                            </v-card-title>
                            <v-spacer></v-spacer>
                            <v-card-text class="elevation-1">
                                <b>
                                <v-text-field
                                label="Logradouro:"
                                :rules="[rules.required]"
                                v-model='dadosDoUsuario.endereco.logradouro'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Bairro:"
                                :rules="[rules.required]"
                                v-model='dadosDoUsuario.endereco.bairro'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Número:"
                                :rules="[rules.required]"
                                v-model='dadosDoUsuario.endereco.numero'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Complemento:"
                                v-model='dadosDoUsuario.endereco.complemento'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Cidade:"
                                :rules="[rules.required]"
                                v-model='dadosDoUsuario.endereco.cidade'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Estado:"
                                :rules="[rules.required]"
                                v-model='dadosDoUsuario.endereco.estado'
                                :disabled="!dadosResidenciaisEditaveis"></v-text-field>
                                </b>
                            </v-card-text>
                        </v-card>
                    </v-container>
                </v-flex>

                <v-flex xs6>   
                    <v-container>
                        <v-card>
                            <v-card-title class="elevation-2 light-blue"
                            style="color:white">Dados Pessoais
                                <v-spacer></v-spacer> 
                                <v-btn
                                text
                                @click="dadosPessoaisEditaveis = !dadosPessoaisEditaveis">
                                    <v-icon color="white">mdi-border-color</v-icon>
                                 </v-btn>
                            </v-card-title>
                            <v-spacer></v-spacer>
                            <v-card-text class="elevation-1">
                                <b>
                            
                                <v-text-field
                                label="Nome:"
                                v-model='nome'
                                :rules="[rules.required]"
                                :value="dadosDoUsuario.nome"
                                :disabled="!dadosPessoaisEditaveis"></v-text-field>

                                <v-text-field
                                label="CPF:"
                                v-mask="cpfMask"
                                :rules="[rules.required, rules.validateCPF]"
                                v-model='dadosDoUsuario.cpf'
                                :disabled="!dadosPessoaisEditaveis"></v-text-field>

                                <v-text-field v-if="dadosDoUsuario.cnh != ''"
                                label="CNH:"
                                v-mask="cnhMask"
                                :rules="[rules.required, rules.minCNH]"
                                v-model='dadosDoUsuario.cnh'
                                :disabled="!dadosPessoaisEditaveis"></v-text-field>

                                <v-text-field
                                label="E-mail:"
                                :rules="[rules.required, rules.email]"
                                v-model='dadosDoUsuario.email'
                                :disabled="!dadosPessoaisEditaveis"></v-text-field>

                                <v-text-field
                                label="Telefone:"
                                :rules="[rules.required]"
                                v-mask="telefoneMask"
                                v-model='dadosDoUsuario.telefone'
                                :disabled="!dadosPessoaisEditaveis"></v-text-field>                    
                                </b>
                            </v-card-text>
                            <v-btn
                                v-if="dadosResidenciaisEditaveis != false || dadosPessoaisEditaveis != false"
                                style="color: white; float:right; margin-top: 40px"
                                class="indigo darken-3"
                                @click="editar()">
                                    Salvar
                            </v-btn>
                        </v-card>
                    </v-container>
                </v-flex>  
            </v-layout>
        </v-container>
    </div>
</template>

<script>
import service from './CadastroELoginService.js'
import { mask } from 'vue-the-mask'

export default {

    directives:{
        mask
    },

    created(){
        this.nome = this.userData.nome
        this.dadosDoUsuario = this.userData
    },

    data(){
        return{
            cpfMask: '###.###.###-##',
            cnhMask: '###########',
            telefoneMask: '(##)####-####',
            dadosPessoaisEditaveis: false,
            dadosResidenciaisEditaveis: false,
            nome: '',
            dadosDoUsuario: {},
            rules: {
                required: value => !!value || 'Campo Obrigatório',
                validateCPF: v => v.length == 14 || 'Digite o CPF completo',
                minCNH: v => v.length == 11 || 'Digite uma CNH válida ',
                email: value => {
                const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                return pattern.test(value) || 'Digite um e-mail válido'
                }
            }
        }
    },

    computed: {
        userData(){
            return this.$store.state.usuarioLogado;
        }

    },
    
    methods:{
        async editar(){
            this.dadosDoUsuario.nome = this.nome
            await service.atualiza(this.dadosDoUsuario)
            .then(resposta => console.log(resposta))
            .catch(erro => console.log(erro))
            this.$store.state.usuarioLogado = this.dadosDoUsuario
            this.dadosResidenciaisEditaveis = false
            this.dadosPessoaisEditaveis = false  
        },
    }
}
</script>

<style>

</style>
