import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table'
import { loadLogs } from '../actions'

class FileListPage extends Component {
  static propTypes = {
    loadLogs: PropTypes.func.isRequired,
    logFiles: PropTypes.object.isRequired
  }

 componentDidMount() {
   this.props.loadLogs({endpoint: '/api/filelist'})
 }

  render() {
    const rows = Object.entries(this.props.logFiles).map(([fileName, fileInfo], index) => {
      return (
        <TableRow key={index}>
          <TableRowColumn>{fileName}</TableRowColumn>
          <TableRowColumn>{fileInfo.fileSize}</TableRowColumn>
          <TableRowColumn>{fileInfo.formattedUpdateDate}</TableRowColumn>
        </TableRow>
      )
    })
    return (
      <div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHeaderColumn>ファイル名</TableHeaderColumn>
              <TableHeaderColumn>サイズ(B)</TableHeaderColumn>
              <TableHeaderColumn>更新日</TableHeaderColumn>
            </TableRow>
          </TableHeader>
          <TableBody>{rows}</TableBody>
        </Table>
      </div>
    )
  }
}

function mapStateToProps(state, ownProps) {
  const logFiles = state.entities.logFile || {}
  return {
    state,
    ownProps,
    logFiles
  }
}

export default connect(mapStateToProps, {
  loadLogs
})(FileListPage)
