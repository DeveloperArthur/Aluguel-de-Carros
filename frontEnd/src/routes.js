import Login from './components/loginEcadastro/Login.vue'
import Cadastro from './components/loginEcadastro/Cadastro.vue'
import Cliente from './Cliente/MenuCliente.vue'
import Alugador from './Alugador/MenuAlugador.vue'
import MinhasInformacoes from './components/loginEcadastro/MinhasInformacoes.vue'
import MeusCarros from './Alugador/MeusCarros.vue'
import MinhasLocacoes from './Cliente/MinhasLocacoes.vue'
import Misto from './Misto/Misto.vue'

export const routes = [
    {path: '/login', name: 'login', component: Login},
    {path: '/cadastro', name: 'cadastro', component: Cadastro},
    {path: '/alugador', name: 'alugador', component: Alugador},
    {path: '/cliente', name: 'cliente', component: Cliente},
    {path: '/minhasInformacoes', name: 'informacoes', component: MinhasInformacoes},
    {path: '/meusCarros', name: 'meusCarros', component: MeusCarros},
    {path: '/minhasLocacoes', name: 'minhasLocacoes', component: MinhasLocacoes},
    {path: '/misto', name: 'misto', component: Misto}
]