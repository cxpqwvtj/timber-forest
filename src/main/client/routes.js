import React from 'react'
import { Route,  } from 'react-router'
import App from './containers/App'
import FileListPage from './containers/TimberListPage'

export default (
  <Route path={`${process.env.CONTEXT_PATH}/`} component={App}>
    <Route path='timber/list' component={FileListPage} />
  </Route>
)
