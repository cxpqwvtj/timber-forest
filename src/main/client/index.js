import 'babel-polyfill'
import React from 'react'
import { render } from 'react-dom'
import { browserHistory } from 'react-router'
import { syncHistoryWithStore } from 'react-router-redux'
import Root from './containers/Root'
import configureStore from './store/configureStore'
import rootSaga from './sagas/sagaIndex'

const store = configureStore()
store.runSaga(rootSaga)
const history = syncHistoryWithStore(browserHistory, store)

render(
  <div>
    <Root store={store} history={history} />
  </div>,
  document.getElementById('root')
)