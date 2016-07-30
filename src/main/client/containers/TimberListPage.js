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
   this.props.loadLogs()
 }

  render() {
    const rows = Object.entries(this.props.logFiles).map(([fileName, fileInfo]) => {
      return (
        <TableRow key={fileName}>
          <TableRowColumn>{fileInfo.hostName}</TableRowColumn>
          <TableRowColumn>{fileInfo.appName}</TableRowColumn>
          <TableRowColumn>{fileInfo.fileSize}</TableRowColumn>
          <TableRowColumn>{fileInfo.createdDate}</TableRowColumn>
          <TableRowColumn>{fileInfo.uploadDate}</TableRowColumn>
        </TableRow>
      )
    })
    return (
      <div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHeaderColumn>HOST NAME</TableHeaderColumn>
              <TableHeaderColumn>APP NAME</TableHeaderColumn>
              <TableHeaderColumn>SIZE(Byte)</TableHeaderColumn>
              <TableHeaderColumn>CREATED DATE</TableHeaderColumn>
              <TableHeaderColumn>UPLOAD DATE</TableHeaderColumn>
            </TableRow>
          </TableHeader>
          <TableBody stripedRows={true} showRowHover={true}>{rows}</TableBody>
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
