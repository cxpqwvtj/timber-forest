import 'isomorphic-fetch'

function callApi(requestParam) {
  const fullUrl = `${process.env.CONTEXT_PATH}${requestParam.endpoint}`

  return fetch(fullUrl)
    .then(response =>
      response.json().then(json => ({ json, response }))
    ).then(({ json, response }) => {
      if (!response.ok) {
        return Promise.reject(json)
      }

      return Object.assign({},
        json,
        { nextParam: 'nextParam' }
      )
    })
    .then(
      response => ({response}),
      error => ({error: error.message || 'Something bad happened'})
    )
}

// api services
export const fetchLogs = requestParam => callApi(requestParam)
