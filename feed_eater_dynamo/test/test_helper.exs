Dynamo.under_test(FeedEaterDynamo.Dynamo)
Dynamo.Loader.enable
ExUnit.start

defmodule FeedEaterDynamo.TestCase do
  use ExUnit.CaseTemplate

  # Enable code reloading on test cases
  setup do
    Dynamo.Loader.enable
    :ok
  end
end
