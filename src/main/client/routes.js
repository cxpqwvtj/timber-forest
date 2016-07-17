import React from 'react'
import { Route,  } from 'react-router'
import App from './containers/App'
import FileListPage from './containers/FileListPage'

export default (
  <Route path={`${process.env.CONTEXT_PATH}/`} component={App}>
    <Route path='file/list' component={FileListPage} />
  </Route>
)
