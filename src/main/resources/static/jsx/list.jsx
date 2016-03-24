var Container = React.createClass({
  render: function() {
    return (
      <div>
        <ListBox />
      </div>
    );
  }
});

var ListBox = React.createClass({
  render: function() {
    return (
      <div>テスト</div>
    );
  }
});

ReactDOM.render(<Container />, document.getElementById("container"));
