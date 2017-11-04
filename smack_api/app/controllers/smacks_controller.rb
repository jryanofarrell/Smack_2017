class SmacksController < ApplicationController
    def index
        @smacks = Smack.all()
        render json: {status: 200,
                      data: @smacks}
    end

    def update
    	@smack = Smack.find(params[:id])
    	puts params.inspect
	    @smack.update(location: params[:location], slot1: params[:slot1])
	    @smacks = Smack.all()
        render json: {status: 200,
                      data: @smacks}
    end
end
