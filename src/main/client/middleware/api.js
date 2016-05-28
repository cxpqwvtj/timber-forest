// Action key that carries API call info interpreted by this Redux middleware.
export const CALL_API = Symbol('Call API')

export default store => next => action => {
  console.log('middleware')
  const callAPI = action[CALL_API]
  if (typeof callAPI === 'undefined') {
    return next(action)
  }

  function actionWith(data) {
    const finalAction = Object.assign({}, action, data)
    delete finalAction[CALL_API]
    return finalAction
  }

  return next(actionWith({ type: callAPI.types[0] }))
}
