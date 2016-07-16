import { take, put, call, fork, select } from 'redux-saga/effects'
import { api } from '../services'
import * as actions from '../actions'

const { logs } = actions

function* fetchEntity(entity, apiFn, id, url) {
  console.log(entity, apiFn, id, url)
  yield put( entity.request(id) )
  const {response, error} = yield call(apiFn, url || id)
  if(response)
    yield put( entity.success(id, response) )
  else
    yield put( entity.failure(id, error) )
}

export const fetchLogs = fetchEntity.bind(null, logs, api.fetchLogs)

function* loadLogs(requestParam) {
  console.log('call loadLogs', requestParam)
  yield call(fetchLogs, requestParam)
}

function* watchLogs() {
  while(true) {
    console.log('watchLogs起動')
    const requestParam = yield take(actions.LOAD_LOGS)
    console.log('watchLogs', requestParam)
    yield fork(loadLogs, requestParam)
  }
}

export default function* root() {
  yield [
    fork(watchLogs)
  ]
}
