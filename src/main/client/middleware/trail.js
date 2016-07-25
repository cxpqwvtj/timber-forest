import 'isomorphic-fetch'

export default store => next => action => {
  const returnedValue = next(action)
  const state = store.getState()
  const actionKey = 'action'
  const messageKey = 'message'
  const [token] = document.cookie.split(';').map((value) => {
    if (value.split('=').map((value) => value === 'XSRF-TOKEN').length >= 0) {
      return value.split('=')[1]
    }
  })
  fetch('/api/trail/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': token
    },
    credentials: 'same-origin',
    body: JSON.stringify({
      [actionKey]: action.type,
      [messageKey]: {action, state},
      'log_level': 'TRACE',
      'tag': 'redux.logger'
    })
  }).then(response =>
      response.json().then(json => ({ json, response }))
    ).then(({ json, response }) => {
      if (json.error || !response.ok) {
        console.log(`error send trail. [status]${response.status} [error]${json.error}`)
      }
    })
  
  return returnedValue
}