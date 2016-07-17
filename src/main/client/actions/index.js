const REQUEST = 'REQUEST'
const SUCCESS = 'SUCCESS'
const FAILURE = 'FAILURE'

function createRequestTypes(base) {
  return [REQUEST, SUCCESS, FAILURE].reduce((acc, type) => {
		acc[type] = `${base}_${type}`
		return acc
	}, {})
}

export const LOGS = createRequestTypes('LOGS')
export const LOAD_LOGS = 'LOAD_LOGS'

function action(type, payload = {}) {
  return {type, ...payload}
}

export const logs = {
  request: (param) => action(LOGS.REQUEST, {param}),
  success: (param, response) => action(LOGS.SUCCESS, {param, response}),
  failure: (param, error) => action(LOGS.FAILURE, {param, error}),
}

export const RESET_ERROR_MESSAGE = 'RESET_ERROR_MESSAGE'

// Resets the currently visible error message.
export function resetErrorMessage() {
  return {
    type: RESET_ERROR_MESSAGE
  }
}

export const loadLogs = () => action(LOAD_LOGS, {endpoint: '/api/file/list'})
