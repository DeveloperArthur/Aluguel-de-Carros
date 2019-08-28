import axios from 'axios'

export default{
  url: 'https://backend-dev-dot-glass-glow-216222.appspot.com',
  lista: function(recurso){
    return axios({
      method: "GET",
      url: `${this.url}${recurso}`,
      responseType: "json",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    })
  },
  novo: function(recurso, objetoJSON){
    return axios({
      method: "POST",
      url: `${this.url}${recurso}`,
      responseType: "json",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      data: objetoJSON
    })
  },
  atualiza: function(recurso, objetoJSON){
    return axios({
      method: 'PUT',
      url: `${this.url}${recurso}`,
      responseType: "json",
      headers: {    
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      data: objetoJSON
    })
  },
  deleta: function(recurso, id){
    return axios({
      method: 'DELETE',
      url: `${this.url}${recurso}/${id}`,
    })
  },

}
