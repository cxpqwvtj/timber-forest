import { take, put, call, fork, select } from 'redux-saga/effects'

function* fetchEntity(entity, apiFn, id, url) {
  yield put( entity.request(id) )
  const {response, error} = yield call(apiFn, url || id)
  if(response)
    yield put( entity.success(id, response) )
  else
    yield put( entity.failure(id, error) )
}

export default function* root() {
  yield [
  ]
}
