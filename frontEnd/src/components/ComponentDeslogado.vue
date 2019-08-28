<template>
    <div>
        <v-dialog 
        v-model="estaLogado"
        max-width="500px">
            <v-card height="275px">
                <v-card-title 
                    class="headline; indigo darken-3"
                    style="color: white"
                    >Atenção
                </v-card-title>
                <br>
                <v-card-text
                style="font-size: 15px"
                class="text-center">
                    <b><p 
                    style="color: black"
                    >Para realizar esta operação, é necessário ter um cadastro.
                    Cadastre-se em nosso site e desfrute dos nossos serviços ou continue navegando
                    e descubra se atendemos suas necessidades.
                    </p></b>
                    <br><br>
                    <v-layout>
                        <v-flex xs6>
                            <v-btn
                            text
                            color="indigo darken-3"
                            style="font-size: 13px"
                            @click="$emit('abreDialogo', estaLogado = false)">
                                Continuar navegando
                            </v-btn>
                        </v-flex>
                        <v-flex xs6>
                            <v-btn
                            text
                            color="indigo darken-3"
                            style="font-size: 13px"
                            @click='realizarCadastroOuLogin()'>
                                Realizar cadastro/login
                            </v-btn>
                        </v-flex>
                    </v-layout>   
                </v-card-text>
            </v-card>
        </v-dialog> 
    </div>    
</template>

<script>
export default {
    
    props: ['estaLogado'],

    methods: {    
        realizarCadastroOuLogin(){
            this.$router.push({path: '/'})
        }
   }

}
</script>

<style>

</style>