import 'isomorphic-fetch'

function callApi(requestParam) {
  const fullUrl = `${process.env.CONTEXT_PATH}${requestParam.endpoint}`
  const param = Object.assign({
      method: requestParam.method,
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }, requestParam.method === 'POST' ? { body: JSON.stringify(requestParam.body || null)} : {})

  return fetch(fullUrl, param
    ).then(response =>
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
export const fetchData = requestParam => callApi(requestParam)
