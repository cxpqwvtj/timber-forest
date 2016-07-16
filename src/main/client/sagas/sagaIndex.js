import { take, put, call, fork, select } from 'redux-saga/effects'
import { api } from '../services'
import * as actions from '../actions'

const { logs } = actions

function* fetchEntity(entity, apiFunction, param, url) {
  yield put( entity.request(param) )
  const {response, error} = yield call(apiFunction, url || param)
  if(response)
    yield put( entity.success(param, response) )
  else
    yield put( entity.failure(param, error) )
}

export const fetchLogs = fetchEntity.bind(null, logs, api.fetchLogs)

function* loadLogs(requestParam) {
  yield call(fetchLogs, requestParam)
}

function* watchLogs() {
  while(true) {
    const requestParam = yield take(actions.LOAD_LOGS)
    yield fork(loadLogs, requestParam)
  }
}

export default function* root() {
  yield [
    fork(watchLogs)
  ]
}
