import { take, put, call, fork, select } from 'redux-saga/effects'
import { api } from '../services'
import * as actions from '../actions'

const { logs, trail } = actions

function* fetchEntity(entity, apiFunction, param, url) {
  yield put( entity.request(param) )
  const {response, error} = yield call(apiFunction, url || param)
  if(response)
    yield put( entity.success(param, response) )
  else
    yield put( entity.failure(param, error) )
}

export const fetchLogs = fetchEntity.bind(null, logs, api.fetchData)
export const fetchTrail = fetchEntity.bind(null, trail, api.fetchData)

function* loadLogs(requestParam) {
  yield call(fetchLogs, requestParam)
}

function* createTrail(requestParam) {
  yield call(fetchTrail, requestParam)
}

function* watchLogs() {
  while(true) {
    const requestParam = yield take(actions.LOAD_LOGS)
    yield fork(loadLogs, requestParam)
  }
}

function* watchCreateTrail() {
  while(true) {
    const requestParam = yield take(actions.CREATE_TRAIL)
    yield fork(createTrail, requestParam)
  }
}

export default function* root() {
  yield [
    fork(watchLogs),
    fork(watchCreateTrail)
  ]
}
