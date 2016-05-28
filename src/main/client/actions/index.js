import { CALL_API } from '../middleware/api'

export const API_REQUEST = 'API_REQUEST'
export const API_SUCCESS = 'API_SUCCESS'
export const API_FAILURE = 'API_FAILURE'

export function load(params, requireFields = []) {
  console.log('actions#load1')
  return (dispatch, getState) => {
    console.log('actions#load2')
    return dispatch(fetchData(params))
  }
}

function fetchData(params) {
  return {
      [CALL_API]: {
        types: [ API_REQUEST, API_SUCCESS, API_FAILURE ],
        endpoint: `api/endpoint${params}`,
        schema: 'schemaName'
    }
  }
}

export const RESET_ERROR_MESSAGE = 'RESET_ERROR_MESSAGE'

// Resets the currently visible error message.
export function resetErrorMessage() {
  return {
    type: RESET_ERROR_MESSAGE
  }
}
