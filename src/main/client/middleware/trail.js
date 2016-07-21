import 'isomorphic-fetch'

export default store => next => action => {
  const returnedValue = next(action)
  const state = store.getState()
  const actionKey = 'action'
  const messageKey = 'message'
  fetch('/api/trail/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
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